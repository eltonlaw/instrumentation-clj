(ns instrumentation-clj.core)

(defn ns-vars
  [namespaces]
  (->> namespaces
       ;; def creates and interns Vars
       (mapcat #(ns-interns %))
       (map (fn [[sym var]]
              {:sym sym
               :var var
               :val (var-get var)}))))

(defn current-ns-vars []
  (ns-vars [*ns*]))
