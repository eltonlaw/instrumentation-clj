(ns instrumentation-clj.dev
  (:require [instrumentation-clj.core :refer :all]))

(defn square [x] (* x x))

(defn ^{:instrument ::foo-logger} foo
  [x]
  (* x x))

(defn bar [x] (* x x))

;; TODO: defs are ignored, add instrumentation to
;; follow value changes
(def a 10)

(instrument-ns
  {:pre
   (fn [{:keys [var-sym]} args]
     (println (format "%s args=%s" var-sym args)))
   :post
   (fn [{:keys [var-sym]} ret]
     (println (format "%s ret=%s" var-sym ret)))})
