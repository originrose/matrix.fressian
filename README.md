# clojure.core.matrix.fressian

Adds Fressian support to core.matrix when using vectorz.


    (require '[clojure.core.matrix :as mat])
    (require '[clojure.core.matrix.fressian :refer (write-array read-array)])

    (mat/set-current-implementation :vectorz)

    ; Write an array out to a file
    (write-array "foo.mat" (mat/array [[1 2] [3 4]]))

    ; Read it back in
    (read-array "foo.mat")

Note, both write-array and read-array create an output-stream or input-stream with 
their first argument, which can be a File, URL, URI, Socket, or String.


## Adding more implementations

This only works with vectorz because we need to know a base class of each
implementation in order to integrate with the fressian reader/writer handler system.
With almost no work it could be extended to support additional implementations.

The binary format is the same for all arrays:
- tag
- [shape vector]
- [doubles...]
