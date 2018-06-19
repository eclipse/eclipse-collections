
This is the basic syntax for Eclipse Collections
## myCollection.iterate({*codeblock*});

### *myCollection*

A collection object or **container**. Some containers are enhancements
of standard Java collection types; these include:

  - **FastList** (ArrayList)
  - **UnifiedSet** (HashSet)
  - **UnifiedMap** (HashMap)

Other collection types implement new interfaces:

  - *Bag* (Multiset)
  - *Multimap*

The key enhancement Eclipse Collections provides is **internal
iteration**: the collection objects themselves furnish the methods for
iterative operations that would otherwise require external **looping**
structures, such as *for* and *while*.

### *iterate*

An **iteration method denoting** **the action to perform on each
element** of the collection and the type of object that is to be
returned (if any). The most commonly-used methods are:

  - **select/reject**
  - **collect**
  - **detect**
  - **all/anySatisfy**
  - **forEach**
  - **injectlnto**
  - **count**

### *codeblock*

A function that is **passed as a parameter** of the iteration method. It
can return various data types, depending on the iteration method used.
The return value can be:

  - a boolean value, using a **Predicate** interface
  - a selected or computed object, using a **Function**
  - nothing, simply execute a **Procedure.**

The body of the iterated code is passed as a parameterized function, that
is, as an **anonymous inner class**. This lets us separate (abstract)
*what is being done* (the code) from *how it is iterated* (the method)
through the collection.

The **code block operates on each element of the collection** as it is
exposed upon iteration. That is, with each iteration, the current data
element is evaluated or transformed by the code block. The code block
can also apply additional arguments passed to it by the calling
iteration method.

