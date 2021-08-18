# instrumentation-clj

Distributed infrastructure is pretty complicated to debug and pinpointing the cause of errors in production is tough when you're missing context about the exact timeline or flow of data. This library provides some utility functions for automatically instrumenting your functions and namespaces.

## Background

Instrumentation

## Usage

Add library to dependencies

    eltonlaw/instrumentation-clj {:mvn/version "0.0.1"}

    (ins/init! (fn [var-map]
                (println "called")))

    (ins/instrument-ns!)
    (ins/instrument-ns! {:exclude ['foo]})

## License

Copyright © 2021 D0nkrs

_EPLv1.0 is just the default for projects generated by `clj-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
