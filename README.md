# Testing in Spring 

Testing api coverage:
- Junit5 (Some notes about Junit4 -> Junit5)
- Mockito
- Hamcrest
- AssertJ
- JsonPath

Tests:
- Unit
- Integration

[*System tests*](https://u-tor.com/topic/system-vs-integration#:~:text=System%20testing%20is%20a%20testing,are%20joined%20and%20tested%20simultaneously.) are not added.


## Testing Performance
_The fast the unit test the fast the feedback._
- Mocks also runs faster (avoid dependencies which could have some delay)
- Reducing scope also makes tests faster (If it's business layer test try not go to another scope if it's possible)
- Try not launch a *spring context* if it's not needed. Spring context loads all the classes (thus all the scopes) and if
we just want a single class to be tested, we should use it but. 
  - If we are doing unit-testing, spring-context should be limited, as in data/repository layers which could be needed.  
- Web layer and data layer need to be as thin as possible (This way me limit the scope
and improve test coverage and performance).


## Best practises 

1. Readable
    - One look at the test, and you know what is being tested
      - Naming, Avoid redundancy, if it has lots of creations should need a class abstraction.
2. Fast
   -  The more slow the more it last to get the result
3. Isolated
   - Fails only when there is an issue with the code.
     -  It should not depend on external things/systems. 
     -  If you depend on a database and its down your test will be failing
     -  Use Mocks, stubs, to remove external layers on our tests
4. Run Often
   - What is the use of having unit tests which are not run frequently?
   - What happens if you don't do commit code often?
   - This will avoid future errors while coding


## Junit4 - Junit5

![Junit4 Changes to Junit5](docs/junit5-changes.png)