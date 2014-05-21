package com.mestrelab.tools;

public class ClienteDAO {

	public boolean createUser(ClienteVO clienteVO){
		if (clienteVO.getName() != null || clienteVO.getName().length()<1 )
			return false;
		
		//actualiza DB
		
		return true;
	}
}
