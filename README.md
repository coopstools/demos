## DEMOS

This page is updated with demonstration that have been given by the author of coopstools. Currently, there are two demos; one on java util's Optional class, and another on java util's stream.

All demos can be found in the tests folder.

For any questions regarding the demos, please leave a comment on this GitHub page. I love feedback, and will include it in the demos if I think it can help others as well.

## java.util.Optional

./src/test/java/com/coopstools/demos/monads/OptionalExamples

This demonstration shows the basics of what can be done with, and how to use, java util's Optional class. This class is useful in any situation where you are dealing with data that is potential null, or which, when passed into a method, may result in a null. This class is also usefull when one wants to write code as a transformation of data; as oppose to as a series of procedures. (Due to advances in Java 9, this demo will be updated after the final release of java 9)

## java.util.stream.Stream

./src/test/java/com/coopstools/demos/monads/StreamExamples

This deomstration shows the basics of how to use java util's streams. Streams are a great way of dealing with collections. They allow for lazy evaluation of data; which can be powerful, but can tricky if one doesn't know some of the ins and outs. As such, this demo covers lazy evaluation in streams in the context of intermediate and terminal operations. (Due to advances in Java 9, this demo will be updated after the final release of java 9)