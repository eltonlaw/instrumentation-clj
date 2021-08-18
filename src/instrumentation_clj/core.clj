(ns instrumentation-clj.core)

(defn ns-vars
  [namespaces]
  (->> namespaces
       ;; def creates and interns Vars
       (mapcat #(ns-interns %))
       (map (fn [[sym var-root]]
              {:var-sym sym
               ;; keys available: arglists, line, column, file, name, ns
               :var-meta (meta var-root)
               :var-root var-root
               :var-val (var-get var-root)}))))

(defn wrap-fn-var
  "Wrap functions so things happen before and/or after"
  [{:keys [var-root] :as env}
   {:keys [pre post]}]
  (alter-var-root
    var-root
    (fn [f]
      (fn [& args]
        ;; TODO: Should somehow allow workflow for a combined single emission
        ;; so that the atomic elements are at a function invokation level
        (when pre
          (pre env args))
        (let [ret (apply f args)]
          (when post
            (post env ret))
          ret)))))

(defn instrument-ns
  [{:keys [pre post]}]
  (let [vars (ns-vars [*ns*])]
    (doseq [v vars]
      ;; wrap-fn-var should only be called on invokable vars
      (when (ifn? (:var-val v))
        ;; TODO: Lookup pre and post with registered
        ;; instrumentation functions and change things
        ;; depending on the meta available
        (wrap-fn-var v {:pre pre
                        :post post})))))
