package com.mestrelab.tools;

public class ClienteFacade {

	private ClienteDAO clientDao;
	public ClienteFacade(){
		this.clientDao = new ClienteDAO();
		
	}
	
	public boolean darAltaCliente(ClienteVO clienteVO){
		
		return this.clientDao.createUser(clienteVO);
	}
}
