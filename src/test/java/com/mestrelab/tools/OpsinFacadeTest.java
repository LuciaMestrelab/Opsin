package com.mestrelab.tools;

import junit.framework.TestCase;
import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureConfig;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult.OPSIN_RESULT_STATUS;

public class OpsinFacadeTest extends TestCase {

	/*
	private ClienteFacade facade= null;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.facade= new ClienteFacade();
	}
	
	
	public void testDarAltaEmptyUser(){
		
		ClienteVO clienteVO = new ClienteVO(" ", "", "");
		boolean result =  this.facade.darAltaCliente(clienteVO);
		assertEquals(false, result);
	}
*/
	
	private OPSINFacade facade= null;

	
	protected void setUp() throws Exception {
		super.setUp();
		this.facade= new OPSINFacade();
	}
	
	public void testNameToStructure(){
		NameToStructure nts = NameToStructure.getInstance();
		assertNotNull("Got a name to structure convertor", nts);
		
	}
	
	public void testParseToSmiles(){
		ResponseVO responseVO =null;

		responseVO = this.facade.getSmiles("2,4,6-trinitrotoluene");

		assertEquals("[N+](=O)([O-])C1=C(C(=CC(=C1)[N+](=O)[O-])[N+](=O)[O-])C",responseVO.getMessage());
		assertEquals(0,responseVO.getTypeError().intValue());
	}
	
	public void testParseToSmilesWithException(){
		ResponseVO smiles = null;		
		smiles = this.facade.getSmiles("ola");
		assertNotNull("No expected exception", smiles.getMessage());
		assertEquals(-1, smiles.getTypeError().intValue());
	}
	
	public void testParseToInChi(){
		//NameToInchi nti = new NameToInchi();		
		//String inchi = nti.parseToInchi("2,4,6-trinitrotoluene");
		ResponseVO responseVO = null;
		
		responseVO = this.facade.getInChi("2,4,6-trinitrotoluene");

		assertEquals("InChI=1/C7H5N3O6/c1-4-6(9(13)14)2-5(8(11)12)3-7(4)10(15)16/h2-3H,1H3",responseVO.getMessage());
		assertEquals(0,responseVO.getTypeError().intValue());
	}
	

	public void testParseToStandardInChi(){
		ResponseVO responseVO = null;
		
		responseVO = this.facade.getStandardInChi("2,4,6-trinitrotoluene");
		
		assertEquals("InChI=1S/C7H5N3O6/c1-4-6(9(13)14)2-5(8(11)12)3-7(4)10(15)16/h2-3H,1H3",responseVO.getMessage());
		assertEquals(0,responseVO.getTypeError().intValue());
	}	
	
	
	
	public void testParseToCML(){
		//NameToStructure nts = NameToStructure.getInstance();
		//Element cml = nts.parseToCML("ethane");
		
		ResponseVO responseVO = null;
		responseVO = this.facade.getCml("ethane");
	
		assertEquals("Parsing 'ethane'", "<cml xmlns=\"http://www.xml-cml.org/schema\" " +
				"xmlns:conventions=\"http://www.xml-cml.org/convention/\" " +
				"xmlns:cmlDict=\"http://www.xml-cml.org/dictionary/cml/\" " +
				"xmlns:nameDict=\"http://www.xml-cml.org/dictionary/cml/name/\" " +
				"convention=\"conventions:molecular\"><molecule id=\"m1\">" +
				"<name dictRef=\"nameDict:unknown\">ethane</name><atomArray>" +
				"<atom id=\"a1\" elementType=\"C\"><label value=\"1\" dictRef=\"cmlDict:locant\" /><label value=\"alpha\" dictRef=\"cmlDict:locant\" /></atom>" +
				"<atom id=\"a2\" elementType=\"C\"><label value=\"2\" dictRef=\"cmlDict:locant\" /><label value=\"beta\" dictRef=\"cmlDict:locant\" /></atom>" +
				"<atom id=\"a3\" elementType=\"H\" />" +
				"<atom id=\"a4\" elementType=\"H\" />" +
				"<atom id=\"a5\" elementType=\"H\" />" +
				"<atom id=\"a6\" elementType=\"H\" />" +
				"<atom id=\"a7\" elementType=\"H\" />" +
				"<atom id=\"a8\" elementType=\"H\" />" +
				"</atomArray><bondArray>" +
				"<bond id=\"a1_a2\" atomRefs2=\"a1 a2\" order=\"S\" />" +
	            "<bond id=\"a1_a3\" atomRefs2=\"a1 a3\" order=\"S\" />" +
	            "<bond id=\"a1_a4\" atomRefs2=\"a1 a4\" order=\"S\" />" +
	            "<bond id=\"a1_a5\" atomRefs2=\"a1 a5\" order=\"S\" />" +
	            "<bond id=\"a2_a6\" atomRefs2=\"a2 a6\" order=\"S\" />" +
	            "<bond id=\"a2_a7\" atomRefs2=\"a2 a7\" order=\"S\" />" +
	            "<bond id=\"a2_a8\" atomRefs2=\"a2 a8\" order=\"S\" />" +
				"</bondArray></molecule></cml>", responseVO.getMessage());
		
		assertEquals(0,responseVO.getTypeError().intValue());
	}
	

	public void testWarnRatherThanFailOnUninterpretableStereochemistry() {
		NameToStructure n2s = NameToStructure.getInstance();
		NameToStructureConfig n2sConfig = NameToStructureConfig.getDefaultConfigInstance();
		n2sConfig.setWarnRatherThanFailOnUninterpretableStereochemistry(false);
		OpsinResult or = n2s.parseChemicalName("(R)-2,2'-Bis(diphenylphosphino)-1,1'-binaphthyl", n2sConfig);
		assertEquals(OPSIN_RESULT_STATUS.FAILURE, or.getStatus());

		n2sConfig.setWarnRatherThanFailOnUninterpretableStereochemistry(true);
		or = n2s.parseChemicalName("(R)-2,2'-Bis(diphenylphosphino)-1,1'-binaphthyl", n2sConfig);
		assertEquals(OPSIN_RESULT_STATUS.WARNING, or.getStatus());
	}
	
	public void testParseToMolfile(){
		//NameToStructure nts = NameToStructure.getInstance();
		//Element cml = nts.parseToCML("ethane");
		
		ResponseVO responseVO = null;
		responseVO = this.facade.getMolfile("cis-1,4-dimethylcyclohexane");
		
		assertNotNull(responseVO);
		assertTrue(responseVO.getMessage().length()>40);
		
	}
	
}
