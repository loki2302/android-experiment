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

public class UpdateTaskApiCall implements ApiCall<TaskDto> {
	private final String sessionToken;
	private final int taskId;
	private final TaskDescriptionDto taskDescriptionDto;
	
	public UpdateTaskApiCall(String sessionToken, int taskId, TaskDescriptionDto taskDescriptionDto) {
		this.sessionToken = sessionToken;
		this.taskId = taskId;
		this.taskDescriptionDto = taskDescriptionDto;
	}

	@Override
	public ServiceResultDto<TaskDto> performApiCall(String apiRootUrl, RestTemplate restTemplate) {
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("sessionToken", sessionToken);
		uriVariables.put("taskId", taskId);
		
		URI uri = UriComponentsBuilder
				.fromUriString(apiRootUrl)
				.path("/UpdateTask")
				.query("sessionToken={sessionToken}&taskId={taskId}")					
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