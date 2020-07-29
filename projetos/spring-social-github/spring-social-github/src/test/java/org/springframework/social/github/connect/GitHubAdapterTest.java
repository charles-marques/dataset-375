/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.github.connect;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.github.api.UserOperations;

public class GitHubAdapterTest {

	private GitHubAdapter apiAdapter = new GitHubAdapter();
	
	private GitHub github = Mockito.mock(GitHub.class);
	
	@Test
	public void fetchProfile() {
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		when(github.userOperations()).thenReturn(userOperations);
		when(userOperations.getUserProfile()).thenReturn(new GitHubUserProfile(123456L, "habuma", "Craig Walls", "Plano, TX", "SpringSource", null, "cwalls@vmware.com", null, null));
		UserProfile profile = apiAdapter.fetchUserProfile(github);
		assertEquals("Craig Walls", profile.getName());
		assertEquals("Craig", profile.getFirstName());
		assertEquals("Walls", profile.getLastName());
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("habuma", profile.getUsername());
	}
	
	@Test
	public void fetchProfileFirstNameOnly() {
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		when(github.userOperations()).thenReturn(userOperations);
		when(userOperations.getUserProfile()).thenReturn(new GitHubUserProfile(123456L, "habuma", "Craig", "Plano, TX", "SpringSource", null, "cwalls@vmware.com", null, null));
		UserProfile profile = apiAdapter.fetchUserProfile(github);
		assertEquals("Craig", profile.getName());
		assertEquals("Craig", profile.getFirstName());
		assertNull(profile.getLastName());
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("habuma", profile.getUsername());
	}

	@Test
	public void fetchProfileMiddleName() {
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		when(github.userOperations()).thenReturn(userOperations);
		when(userOperations.getUserProfile()).thenReturn(new GitHubUserProfile(123456L, "habuma", "Michael Craig Walls", "Plano, TX", "SpringSource", null, "cwalls@vmware.com", null, null));
		UserProfile profile = apiAdapter.fetchUserProfile(github);
		assertEquals("Michael Craig Walls", profile.getName());
		assertEquals("Michael", profile.getFirstName());
		assertEquals("Walls", profile.getLastName());
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("habuma", profile.getUsername());
	}
	
	@Test
	public void fetchProfileExtraWhitespace() {
		UserOperations userOperations = Mockito.mock(UserOperations.class);
		when(github.userOperations()).thenReturn(userOperations);
		when(userOperations.getUserProfile()).thenReturn(new GitHubUserProfile(123456L, "habuma", "Michael    Craig Walls", "Plano, TX", "SpringSource", null, "cwalls@vmware.com", null, null));
		UserProfile profile = apiAdapter.fetchUserProfile(github);
		assertEquals("Michael    Craig Walls", profile.getName());
		assertEquals("Michael", profile.getFirstName());
		assertEquals("Walls", profile.getLastName());
		assertEquals("cwalls@vmware.com", profile.getEmail());
		assertEquals("habuma", profile.getUsername());
	}

}
