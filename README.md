This project provides the implementation of a coding and decoding system based on the following problem.

Let's suppose we have an information source F that has an alphabet and probabilities associated with 
each element of the alphabet p. With m>=1, we want to encode a message of length m emitted by the source 
using arithmetic coding in base 10, dividing the process into two main steps:

 1- Assigning the message m to a sub-interval I contained between [0, 1).
 2- Providing the shortest decimal representation of a number within interval I.
 
Take into account that im using Oracle OpenJDK 21.0.1 to run this project.