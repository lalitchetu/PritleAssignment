package com.pritle.assignment1.controller;

import com.pritle.assignment1.dao.TopicDao;
import com.pritle.assignment1.dao.UserDao;
import com.pritle.assignment1.domain.Topic;
import com.pritle.assignment1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * © 2017 Pritle Holding B.V. - All Rights Reserved See LICENSE file or
 * http://pritle.com for further details
 *
 * @author Timofey Tsymlov <dev@pritle.com>
 * @version 01/02/2017
 */
@RestController
public class TopicController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TopicDao topicDao;

	@Autowired
	private TransactionTemplate transactionTemplate;
	private static final Object LOCK = new Object();

	@RequestMapping(value = "/topic/{topicId}", method = POST)
	public ResponseEntity vote(@RequestHeader("access-token") String accessToken, @PathVariable Long topicId) {
		return transactionTemplate.execute((transactionStatus) -> {
			User user = userDao.findUser(accessToken);
			if (user == null) {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
			Topic topic = topicDao.findTopic(topicId);
			if (topic == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			if (user.getVotesRemain() > 0) {

				synchronized (LOCK) {
					topic = topicDao.findTopic(topicId);
					topic.setVotes(topic.getVotes() + 1);
					user.setVotesRemain(user.getVotesRemain() - 1);

					userDao.update(user);
					topicDao.update(topic);
					//topicDao.updateTopic(topic); // Other way to solve the problem
				}

				return new ResponseEntity(HttpStatus.OK);

			} else {
				// User can't vote anymore
				return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
			}
		});
	}

	@RequestMapping(value = "/topic/{topicId}", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity getTopic(@PathVariable Long topicId) {
		Topic topic = topicDao.findTopic(topicId);
		if (topic == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(topic);
		}
	}

}
