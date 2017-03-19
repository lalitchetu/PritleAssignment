package com.pritle.assignment1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerVoteTest {

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
	public void topicVotingTest1() {

		final long topicIdToVote = 2;
		final String user1Token = "user-1";
		final int amountOfVotesPerUser = 30;

		for (int votesRemain = amountOfVotesPerUser; votesRemain > 0; votesRemain--) {
			assertThat(voteForTopic(user1Token, topicIdToVote), equalTo(HttpStatus.OK));
		}
	}

	@Test
	public void topicVotingTest2() {

		final long topicIdToVote = 2;
		final String user2Token = "user-2";
		final int amountOfVotesPerUser = 30;

		for (int votesRemain = amountOfVotesPerUser; votesRemain > 0; votesRemain--) {
			assertThat(voteForTopic(user2Token, topicIdToVote), equalTo(HttpStatus.OK));
		}

	}

	@Test
	public void topicVotingTest3() {

		final long topicIdToVote = 2;
		final String user3Token = "user-3";
		final int amountOfVotesPerUser = 30;

		for (int votesRemain = amountOfVotesPerUser; votesRemain > 0; votesRemain--) {
			assertThat(voteForTopic(user3Token, topicIdToVote), equalTo(HttpStatus.OK));
		}
	}

	@Test
	public void topicVotingTest4() {

		final long topicIdToVote = 2;
		final String user4Token = "user-4";
		final int amountOfVotesPerUser = 30;

		for (int votesRemain = amountOfVotesPerUser; votesRemain > 0; votesRemain--) {
			assertThat(voteForTopic(user4Token, topicIdToVote), equalTo(HttpStatus.OK));
		}

	}

	private HttpStatus voteForTopic(String accessToken, long topicId) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("access-token", accessToken);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(topicUrl, HttpMethod.POST, httpEntity, String.class, topicId).getStatusCode();
	}

}