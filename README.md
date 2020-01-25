# Petition Service for OpenClassrooms REST APIs with Spring Data course

This project contains the source code to accompany my OpenClassrooms
course on building RESTful APIs with Spring Data. The goal is to 
demonstrate the two together we we incrementally work through a solution.

Individual steps are contained within the branches.

# This Branch

Now that you've seen how we can add new relationships, have a go at relating our Petition to the Activist that sponsored it.

A petition will need at least one sponsor in order to exist. Each activist can sponsor from zero to many petitions. It is the hope of the business that those who are passionate about a cause will choose to become sponsor of a new petition.

Here's what you should do:

* Checkout the branch activity-add-sponsor-relationship
* Modify the Petition entity and add a field of type Activity called sponsor.
* Add the appropriate annotation to represent a many to 1 cardinality.
* Once you're done, checkout the branch **solution-add-sponsor-relationships** See how your solution compares with my solution there.


# To run

```./mvnw spring-boot:run```

# To run the tests

```./mvnw test```

Checkout the full course at Openclassrooms.com
