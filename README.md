# instrumentation-clj

Distributed infrastructure is pretty complicated to debug and pinpointing the cause of errors in production is tough when you're missing context about the exact timeline or flow of data. This library provides some utility functions for automatically instrumenting your functions and values.

## Usage

Add library to dependencies

    eltonlaw/instrumentation-clj {:mvn/version "0.0.1"}

To add pre/post instrumentation to all functions in a namespace

    (require '[instrumentation-clj.core :as in])
    (in/instrument-ns
      {:pre
       (fn [{:keys [var-sym]} args]
         (println (format "%s args=%s" var-sym args)))
       :post
       (fn [{:keys [var-sym]} ret]
         (println (format "%s ret=%s" var-sym ret)))})

## License

Copyright © 2021 Elton Law

Distributed under the Eclipse Public License version 1.0.
