package com.google.educloud.cloudserver.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.educloud.internal.entities.VirtualMachine;

public class VirtualMachineDao {

	private static int currentId = 0;

	private static List<VirtualMachine> virtualMachines = new ArrayList<VirtualMachine>();

	private static VirtualMachineDao dao;

	private VirtualMachineDao() {
	}

	public static VirtualMachineDao getInstance() {
		if (null == dao) {
			dao = new VirtualMachineDao();
		}

		return dao;
	}

	public void insert(VirtualMachine machine) {
		machine.setId(++currentId);
		virtualMachines.add(machine);
	}

	public VirtualMachine findById(int id) {
		for (VirtualMachine vm : virtualMachines) {
			if (vm.getId() == id) {
				return vm;
			}
		}
		return null;
	}

	public List<VirtualMachine> getAll() {
		return virtualMachines;
	}

	public void remove(VirtualMachine vm) {
		virtualMachines.remove(vm);
	}

	public void changeState(VirtualMachine machine) {
		VirtualMachine findById = findById(machine.getId());
		findById.setState(machine.getState());
		findById.setUUID(machine.getUUID());
		findById.setVboxSession(machine.getVboxSession());
	}
}