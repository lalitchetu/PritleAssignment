You’re given a simple topic voting application in a git repository which operates on users and topics. Users can vote for topics. 

Each user has limited amount of votes to spend. When user votes for a topic, system decreases amount of user's votes remaining by 1 and increases topic votes by 1. When user has 0 remaining votes, he is not allowed to vote anymore.

### Domain model:
1. User
* accessToken [String]     - secret token to authorise user in system
* votesRemain [int]        - # votes user still have
2. Topic
* id [long]   [long]       - Topic identifier
* description [String]     - topic description
* votes       [int]        - total amount of votes for this topic
 
Topic voting implemented in 
`com.pritle.assignment1.controller.TopicController#vote` and tested by  `com.pritle.assignment1.controller.TopicControllerTest#topicVotingTest` (test passes)
 
 
The task.
It is known that current `TopicController#vote` implementation is not 100% accurate regarding to votes counting.
1. Update `TopicControllerTest#topicVotingTest` or implement different test in a way that shows the problem (your test should fail).
2. Modify `TopicController#vote` to fix the problem (your test must pass now).
3. Describe what other ways to fix the problem you know. What are their pros and cons?

Apply fixes as repository commits. Use git branches/tags if you wish to provide more then one solution.

To run tests, execute in terminal:   `gradlew test`
To run server locally, execute   `gradlew bootRun`
You can test it now:   `curl http://localhost:8080/topic/1`


Developer Comment:
1. In votes counting I identified issue that is for one topic there are concurrent request, the count of votes are not incrementing accurately.

2. Implemented TopicControllerTestSuite which run parallel test of TopicControllerVoteTest for 4 users.
   For 4 voted 30 vote each for topic 2, so finally the vote count of topic 2 should be 120.
   
3. For concurrent request the test is failed.

4. Modified TopicController#vote my implementing synchronized block.
   Now that TopicControllerTestSuite test pass.

5. Other way to fix the issue is to handle this at the database level.
   add a new method in TopicDao#updateTopic, which can be called from the controller.


