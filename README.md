# clojure.core.matrix.fressian

Adds Fressian support to core.matrix using vectorz, although with very little
work it could be extended to support additional implementations.  We just need
the class or classes that should be written out and read as arrays.  The binary
format is the same for all arrays:

- tag
- [shape vector]
- [doubles...]
