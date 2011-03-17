package com.google.educloud.api.clients;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.educloud.api.EduCloudConfig;
import com.google.educloud.api.EduCloudFactory;
import com.google.educloud.api.entities.EduCloudErrorMessage;
import com.google.educloud.api.entities.Template;
import com.google.educloud.api.entities.VirtualMachine;
import com.google.educloud.api.entities.exceptions.EduCloudServerException;

public class EduCloudVMClientTest {

	private EduCloudVMClient vmClient;

	@Before
	public void setUp() throws Exception {
		// setup client configuration
		EduCloudConfig config = new EduCloudConfig();
		config.setHost("localhost");
		config.setPort(8000);

		vmClient = EduCloudFactory.createVMClient(config);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartVM() throws EduCloudServerException {
		// setup new virtual machine
		Template template = new Template();
		template.setName("lamp-server");
		template.setOsType("linux");
		template.setOsVersion("v6.3");

		VirtualMachine machine = new VirtualMachine();
		machine.setTemplate(template);
		machine.setName("ubuntu-machine");

		// start machine
		VirtualMachine vm = vmClient.startVM(machine);

		System.out.println(vm.getId());
		System.out.println(vm.getName());
		System.out.println(vm.getState());
	}
	
	@Test
	public void testgetAll() throws EduCloudServerException {
		// Ajustar poara listar todas
		// List all.
		List<VirtualMachine> listaMaquinasVirtuais = vmClient.getAll();
		
		System.out.println("Listagem das m�quinas virtuais");
		
		for( VirtualMachine vm : listaMaquinasVirtuais ){
		
			System.out.println(vm.getId());
			System.out.println(vm.getName());			
		}				
	}
	
	@Test
	public void testStopVM() throws EduCloudServerException {
		// setup new virtual machine
		Template template = new Template();
		template.setName("lamp-server");
		template.setOsType("linux");
		template.setOsVersion("v6.3");

		VirtualMachine machine = new VirtualMachine();
		machine.setTemplate(template);
		machine.setName("ubuntu-machine");

		// stop machine
		
		vmClient.stopVM(machine);
	}

	@Test
	public void testStartVMValidationError() {
		// setup new virtual machine
		Template template = new Template();
		template.setName("lamp-server");
		template.setOsType("linux");
		template.setOsVersion("v6.3");

		VirtualMachine machine = new VirtualMachine();
		machine.setTemplate(template);
		machine.setName("ubuntu-machine");

		machine.setId(77777); // introduce error

		try {
			// try start machine
			vmClient.startVM(machine);
		} catch (EduCloudServerException e) {
			assertEquals("Apparently you are trying to start an existing virtual machine", e.getMessage());
			EduCloudErrorMessage errorMessage = e.getErrorMessage();

			assertEquals("CS-001", errorMessage.getCode());
			assertEquals("Set virtual machine id to zero and try again", errorMessage.getHint());
			assertEquals("Apparently you are trying to start an existing virtual machine", errorMessage.getText());
		}
	}

}
