# clojure.core.matrix.fressian

Adds Fressian read/write handler support for writing data that includes
core.matrix arrays.

    (require '[clojure.core.matrix :as mat])
    (require '[clojure.core.matrix.fressian :refer (write-array read-array)])

    (mat/set-current-implementation :vectorz)

    ; Write an array out to a file
    ; To keep this library implementation independent, you need to pass the
    ; (base) class of the matrix type you are using so Fressian knows to use
    ; the correct write handler.
    (write-data "foo.mat" {:src :seti
                           :timestamp (new java.util.Date)
                           :data (mat/array [[1 2] [3 4]])}
                 mikera.arrayz.impl.AbstractArray)

    ; Read it back in
    (read-data "foo.mat")

Both write-data and read-data create an <output|input>-stream with their first
argument, which can be a File, URL, URI, Socket, or String.

NOTE: This currently writes arrays as a dense sequence of doubles, so sparse
matrices will probably not work correctly.

The binary format is the same for all arrays:
- tag
- [shape vector]
- [sequence of doubles...]
