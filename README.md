## __Project 2__
### Due Date
- Week 12, June 2nd, 5PM

--------------------------------------------------

### _NEW FEATURES TRACKER_
- [ ] xxx
- [ ] xxx
- [ ] xxx

--------------------------------------------------

### _Requirements_
- Develop `GUI`.
- Integrate `database`.
- Implement `design patterns`.
- Eliminate `code smell`.
- Use `git`, commit history must be included, `.git` or from GitHub.
- Develop `unit tests (JUnit)`, testing some important functionalities.
- Apply `OOP` (abstraction, encapsulation, inheritance, polymorphism).
- Follow `SOLID` principles.
- Create multiple classes with relationships.
- Bug-free and have `robust error handling`.

#### Requirements Database
- Include `database`, Apache Derby DB (JavaDB), no other types allowed.
- Must run without any configuration.
- DB should be setup automatically without manual configurations
- DB Service must launch automatically

#### Requirements Report
- Less than one-page.
- State project setup.
- GitHub URL (if exists).
- Contribution of each teammate.

#### Requirements Video
- Less than 5min.
- Demonstration, showing all features by running project.
- Code explanation, explaining class structures, methods and processess.

--------------------------------------------------

### _Submission_
1. Project folder, all source codes and related files.
2. Report.
3. Video.
4. DB folder `.zip`, can set DB dir inside project folder.
5. `.git` folder, having all historical commits and branches.

--------------------------------------------------

### _Marking_
#### __(20%) User Interface (GUI)__
- Clear and well-designed GUI following common standards.
- Interface is easy for users to interact with.
```
- User-friendly error messages.
- GUI components organized nicely.
- Quit at any step.
- Go back to menu, restart system at anytime (Logout)
```

#### __(20%) Database__
- Project contains database elements.
- Database interactions and operations(I/O) using JDBC/Hibernate are part of code.
- JDBC/Hibernate must contribute to the functionalities of the project.
```
- Database component manages data READ and WRITE.
- More than 3 database READ interations.
- More than 3 database WRITE interations.
- Derby Embedded mode is utilized.
- Program can connect to database without starting db service.
```

#### __(20%) Software Functionality & Usability__
- Program is easy to compile and run without and manual configurations (e.g., setup DB, import .jar ...).
- The users interact with the program without any errors (e.g., the program can handle invalid data input bu giving clear error messages and instructions).
- Complexity of the functionality.
```
- Program can run wihtout run time error by giving expected inputs.
- No manual configurations.
- Functionality of progra, is complex enough, having most common features of the project implemented.
- Implemented features are robust.
- Any input can be handled perfectly without throwing exceptions.
- Messages can be prompted to end-user for correct input.
```

#### __(30%) Software Design & Implementation__
- Program can be compiled successfully.
- The purpose of the code is easy to understand by reading it.
- The comments in the code are useful and appropriate.
- The code executes without runtime errors.
- Version control is applied.
- Error handling is thorough and robust.
- Class structure reflects good design.
- Design patters are implemented correctly where appropriate.
- Good coding style is used, following appropriate coding standards.
- Code design follows OO design good practice.
- There are no obvious code smells.
- Sufficient test cases are included, which are well written.
- Important functionalities are properly tested.
```
- Comments of methods are given, code easy to read.
- No obvious code smells.
- Version control applied.
- More than 11 reasonable classes (12+) with reasonable methods.
- Class relationships well presented.
- All OOP concepts applied.
- SOLID applied where appropriate.
- Design patterns applied where appropriate.
- Well structured, clear seperation frontend, backend. (UI & Model).
- Robust, handle all inputs without throwing exceptions.
- 5+ well written test cases.
- Tests are well-named.
- Test cover important functionality extensively.
```

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------