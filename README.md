# Project Goal
The goal of this project is to: Develop and Operate a Web Application for Petitions (creating, viewing, and signing petitions)
following DevOps automation mechanisms

## Project Title
DarrensPetitions

## Table of Contents
- [Project Goal](#project-goal)
- [Project Title](#project-title)
- [Requirements](#requirements)
- [Features](#features)

## Requirements
This project requires the following minimum deliverables:
1. Web App
* 1 page to create a petition
* 1 page to view all petitions
* 1 page to search for a petition + 1 page with the result
* Each petition could be clicked
    * 1 page to view the petition and sign it - name and email
    * It is not mandatory to use a database - Lists can be used to store data
    * Populate the app with random data to demonstrate functionality

2. Development
* Use GitHub as the Version Control System
    * Have at least 1 commit per feature - page in the web application
    * Store Jenkinsfile in GitHub
    * Configure GitHub Webhook

3. Continuous Deployment
* Use Jenkins CI/CD pipelines - using Jenkinsfile
* Running on AWS EC2
* Automatically run after each commit
* The following stages are required:
    *  Get code from GitHub
    *  Build
    *  Test
    *  Package and archive as a War file
    * named (for this project) DarrensPetitions.war
    * Deploy(ReDeploy) if the user approves it
    * As a Tomcat container
    * On EC2
    * Publicly accessible at http://SOME_EC2_PUBLIC_IP:9090

## Features
