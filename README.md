# Secure Web Based Application for File-Sharing

![GitHub contributors](https://img.shields.io/github/contributors/scottydocs/README-template.md)  
University: Technical University of Moldova  
Specialty: Software Engineering  
Group: FAF-182  

The application of security in web application is of profound importance due to the extended use  
of web for business. Most of the attacks, are either because the developers are not considering  
security as a concern or due to the security flaws in designing and developing the applications.  
The enforcement of security in the software development life cycle of the application may  
reduce the high cost and efforts associated with implementing security at a later stage. For  
this purpose, we are designing and developing a secure web based application for file sharing.  
Web based file sharing is a method of using a central repository to store files to be shared,  
and making those files available to others in a way that gives you control over who can see  
which files, and over what they can do with the files that they see.  

For more information see the report to this project.  
## Prerequisites

Before you begin, ensure you have met the following requirements:
* You have installed `Java 1.8`
* You have a `Windows/Linux/Mac` machine.
* You have read `Project Report`.
* For other dependency versions used in the project check the `pom.xml`

## Technologies Used:
* Maven  
* Hibernate  
* Spring Boot
* Spring Security
* MySQL
## Using Project

To use the project, follow these steps:

Import project as existing one from pom.xml file, it will configure automatically everything  
you need for running the app. Then create a DB in MySQL called file_sharing_system(don't forget  
to replace application.properties with your data. In order to import project in Intellij Idea  
following steps should be taken:
```
File -> New->From Existing resources -> Pick pom.xml and OK
```
Add run the class _FileSharingApplication_  
The port is set to 8083, you can change this in application.properties file too.

## Implementation Team

| <a href="https://github.com/DivineBee" target="_blank">**Vizant Beatrice**</a> | <a href="https://github.com/whysoserious97" target="_blank">**Lesco Andrei**| <a href="https://github.com/ciutacst" target="_blank">**Ciutac Stefanie**</a>
| :---: |:---:| :---:|
| [![Vizant Beatrice](https://avatars0.githubusercontent.com/u/49019844?s=200&u=b232b6a4e7d387d304f0b7938eabe6cf742bacb8&v=4)](http://github.com/DivineBee)    | [![Lesco Andrei](https://avatars2.githubusercontent.com/u/53511833?s=200&u=4b5de9bd5272530cf96b9d5a174dc6af3e3ecbf0&v=4)](http://github.com/whysoserious97) | [![Ciutac Stefanie](https://avatars2.githubusercontent.com/u/36203071?s=200&u=02ff9cbd96b3110886edb6d5766615988dffa2ff&v=4)](https://github.com/ciutacst) |
| <a href="//github.com/DivineBee" target="_blank">`github.com/DivineBee`</a> | <a href="http://github.com/whysoserious97" target="_blank">`github.com/whysoserious97`</a> | <a href="https://github.com/ciutacst" target="_blank">`github.com/ciutacst`</a> 

## Status
Project is: _finished_