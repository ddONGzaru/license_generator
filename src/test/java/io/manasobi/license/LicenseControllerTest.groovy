package io.manasobi.license

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import io.manasobi.commons.constant.Result
import io.manasobi.security.MongoDBUserDetailsService
import io.manasobi.security.UserDetailsService.UserDetailsServiceImpl;

import java.security.Principal

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import spock.lang.Shared
import spock.lang.Specification



class LicenseControllerTest extends Specification {
	
	@Shared MockMvc mockMvc
	@Shared	LicenseController controller
	@Shared	LicenseService service
	
	
	private def setup() {
		controller = new LicenseController()
	}
	
	private def buildMockMvc(LicenseController controller) {
		
		mockMvc = MockMvcBuilders
			.standaloneSetup(controller)
			.alwaysDo(print())
			.build()
	}
	
	def "URL[/license/publish] Method[Get]"() {
		
		setup:
			buildMockMvc(this.controller)
		
		when:
			def response = mockMvc.perform(get('/license/publish'))
		then:
			response
				.andExpect(handler().handlerType(LicenseController.class))
				.andExpect(handler().methodName('main'))
				.andExpect(view().name('license/publish'))
				.andExpect(status().isOk())
	}
	
	def "URL[/license/publish] Method[Post]"() {
		
		setup:
			service = Mock {
				sendJobTicket(_) >>> [Result.SUCCESS, Result.ERROR_102001]
				saveLicenseDetails(_, _) >> Result.SUCCESS
			}
			
			controller.licenseService = service
			
			buildMockMvc(this.controller)
		
		when:
			def response_success = mockMvc.perform(post('/license/publish'))											
		then:
			response_success
				.andExpect(handler().handlerType(LicenseController.class))
				.andExpect(handler().methodName('publish'))
				//.andExpect(flash().attributeExists('licenseGenKey'))
				.andExpect(flash().attribute('licenseGenKey', null))
				.andExpect(flash().attributeCount(1))
				.andExpect(redirectedUrl('/license/publish/result'))
				.andExpect(status().isFound())
				
		when:
			def response_error = mockMvc.perform(post('/license/publish'))
		then:
			response_error
				.andExpect(handler().handlerType(LicenseController.class))
				.andExpect(handler().methodName('publish'))
				.andExpect(flash().attributeCount(2))
				.andExpect(flash().attribute('licenseGenKey', null))
				.andExpect(flash().attribute('error', 'Amqp 관련 에러입니다.'))
				.andExpect(redirectedUrl('/license/publish/result'))
				.andExpect(status().isFound())		
	}

}





/*
def setupSpec() {
	
	controller = new ConvertStatusController()
	
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/jsp/view/");
	viewResolver.setSuffix(".jsp");
	
	mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.setViewResolvers(viewResolver)
				.alwaysDo(print())
				.build()
}

def "buildFileExtsForSelectTagOptions()"() {
	
	setup:
		controller.fileExtList = ['doc', 'xls', 'ppt']
		def result = '{"options":[{"text":"doc","value":"doc"},{"text":"xls","value":"xls"},{"text":"ppt","value":"ppt"}]}'

	when:
		controller.buildFileExtsForSelectTagOptions()
	then:
		result == controller.fileExtJson
}

def "URL[/conv/inproc] Method[Get]"() {
	
	when:
		def response = mockMvc.perform(get('/conv/inproc'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('handle'))
			.andExpect(view().name('inprocList'))
			.andExpect(status().isOk())
}

def "URL[/conv/success] Method[Get]"() {
	
	setup:
		def fileExtJson = '{"options":[{"text":"doc","value":"doc"},{"text":"xls","value":"xls"},{"text":"ppt","value":"ppt"}]}'
		controller.fileExtJson = fileExtJson
	
	when:
		def response = mockMvc.perform(get('/conv/success'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('handleSuccess'))
			.andExpect(model().attribute('options', fileExtJson))
			.andExpect(view().name('successList'))
			.andExpect(status().isOk())
}

def "URL[/conv/fail] Method[Get]"() {
	
	setup:
		def fileExtJson = '{"options":[{"text":"doc","value":"doc"},{"text":"xls","value":"xls"},{"text":"ppt","value":"ppt"}]}'
		controller.fileExtJson = fileExtJson
	
	when:
		def response = mockMvc.perform(get('/conv/fail'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('handleError'))
			.andExpect(model().attribute('options', fileExtJson))
			.andExpect(view().name('failList'))
			.andExpect(status().isOk())
}

def "URL[/conv/charts] Method[Get]"() {
	
	when:
		def response = mockMvc.perform(get('/conv/charts'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('handleCharts'))
			.andExpect(view().name('charts'))
			.andExpect(status().isOk())
}

def "URL[/conv/refresh] Method[Get]"() {
	
	when:
		def response = mockMvc.perform(get('/conv/refresh'))
	then:
		final NestedServletException exception = thrown()
}

def "URL[/conv/charts/dataset] Method[Post]"() {
	
	setup:
		ChartsService service = Mock()
		service.findChartDataset('timestampParam', 'timeUnitParam') >> {
			'{ result: "charts/dataset" }'
		}
		
		controller.chartsService = service
	
	when:
		def response = mockMvc.perform(post('/conv/charts/dataset')
										.param('timestamp', 'timestampParam')
										.param('timeUnit', 'timeUnitParam'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('findChartDataset'))
			.andExpect(content().contentType('application/json;charset=UTF-8'))
			.andExpect(jsonPath('$.result', is('charts/dataset')))
			.andExpect(status().isOk())
}

def "URL[/usage/cpu] Method[Get]"() {
	
	setup:
		SystemResourceService service = Mock()
		service.cpuUsage >> { '13' }
		
		controller.systemResourceService = service
	
	when:
		def response = mockMvc.perform(get('/usage/cpu'))
										
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('getCpuUsage'))
			.andExpect(content().contentType('text/plain;charset=UTF-8'))
			.andExpect(content().string('13'))
			.andExpect(status().isOk())
}

def "URL[/usage/mem] Method[Get]"() {
	
	setup:
		SystemResourceService service = Mock()
		service.memoryUsage >> { '46' }
		
		controller.systemResourceService = service
	
	when:
		def response = mockMvc.perform(get('/usage/mem'))
										
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('getMemoryUsage'))
			.andExpect(content().contentType('text/plain;charset=UTF-8'))
			.andExpect(content().string('46'))
			.andExpect(status().isOk())
}

def "URL[/conv/status/count] Method[Get]"() {
	
	setup:
		ConvertStatusService service = Mock()
		service.findJobStatusCountByGroup() >> {
			'{ inproc: 1, success: 2, fail: 6 }'
		}
		
		controller.convertStatusService = service
	
	when:
		def response = mockMvc.perform(get('/conv/status/count'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('findJobStatusCountByGroup'))
			.andExpect(content().contentType('application/json;charset=UTF-8'))
			.andExpect(jsonPath('$.inproc', is(1)))
			.andExpect(jsonPath('$.success', is(2)))
			.andExpect(jsonPath('$.fail', is(6)))
			.andExpect(status().isOk())
}

def "URL[/conv/paginate] Method[Post]"() {
	
	setup:
		PagerEntity pagerEntity = new PagerEntity()
		ConvertStatusEntity convertStatusEntity = new ConvertStatusEntity()
	
		ConvertStatusService service = Mock()
		service.paginate(pagerEntity, convertStatusEntity, 'method') >> {
			'{ result: "conv/paginate" }'
		}
		
		controller.convertStatusService = service
	
	when:
		def response = mockMvc.perform(post('/conv/paginate').contentType(MediaType.APPLICATION_JSON)
										.param('endRowNo', '0')
										.param('groupNo', '1')
										.param('pageNo', '0')
										.param('sortType', 'DESC')
										.param('startRowNo', '0')
										.param('totalRecordNo', '0')
										.param('viewPageNo', '10')
										.param('viewRecordNo', '10')
										//.param('fileType', 'txt')
										.param('javascriptMethod', 'method'))
	then:
		response
			.andExpect(handler().handlerType(ConvertStatusController.class))
			.andExpect(handler().methodName('paginate'))
			.andExpect(content().contentType('application/text;charset=UTF-8'))
			.andExpect(jsonPath('$.result', is('conv/paginate')))
			.andExpect(status().isOk())
}

}



.andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
.andExpect(model().attribute("todos", hasSize(2)))
.andExpect(content().contentTypeCompatibleWith("application/json"))
.andExpect(content().encoding("UTF-8"))


mockMvc.perform(get("/byName")
			.sessionAttr("userClientObject", this.userClientObject)
			.param("firstName", firstName)
			.param("lastName", lastName)
	).andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$[0].id").exists())
			.andExpect(jsonPath("$[0].fn").value("Marge"));




*/