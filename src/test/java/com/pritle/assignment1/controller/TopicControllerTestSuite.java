package com.pritle.assignment1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.pritle.assignment1.domain.Topic;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerTestSuite {
	@LocalServerPort
	private int port;

	private String topicUrl;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		topicUrl = "http://localhost:" + port + "/topic/{topicId}";
	}

	@Test
	public void topicVotingCountTest() {
		Class[] cls = { TopicControllerVoteTest.class };

		Result result = JUnitCore.runClasses(ParallelComputer.methods(), cls);
		System.out.println("RunCount: " + result.getRunCount() + "  FailureCount:  " + result.getFailureCount());
		if (result.wasSuccessful()) {
			assertThat("Topic # 2 should has 120 votes (four users, each voted 30 times)", getTopic(2).getVotes(),
					equalTo(120));
		}
	}

	private Topic getTopic(long topicId) {
		return restTemplate.getForEntity(topicUrl, Topic.class, topicId).getBody();
	}
}
