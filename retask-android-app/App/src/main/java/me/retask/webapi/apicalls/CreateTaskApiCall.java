package me.retask.webapi.apicalls;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import me.retask.webapi.ApiCall;
import me.retask.webapi.dto.ServiceResultDto;
import me.retask.webapi.dto.TaskDescriptionDto;
import me.retask.webapi.dto.TaskDto;
import me.retask.webapi.dto.TaskServiceResult;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CreateTaskApiCall implements ApiCall<TaskDto> {
	private final String sessionToken;
	private final TaskDescriptionDto taskDescriptionDto;
	
	public CreateTaskApiCall(String sessionToken, TaskDescriptionDto taskDescriptionDto) {
		this.sessionToken = sessionToken;
		this.taskDescriptionDto = taskDescriptionDto;
	}

	@Override
	public ServiceResultDto<TaskDto> performApiCall(String apiRootUrl, RestTemplate restTemplate) {
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("sessionToken", sessionToken);
		
		URI uri = UriComponentsBuilder
				.fromUriString(apiRootUrl)
				.path("/CreateTask")
				.query("sessionToken={sessionToken}")					
				.buildAndExpand(uriVariables)
				.toUri();
		
		ResponseEntity<TaskServiceResult> result = restTemplate
				.exchange(
						uri, 
						HttpMethod.POST, 
						new HttpEntity<TaskDescriptionDto>(taskDescriptionDto), 
						TaskServiceResult.class);
		
		return result.getBody();
	}		
}