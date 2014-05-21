package com.mestrelab.tools;

import nu.xom.Element;
import uk.ac.cam.ch.wwmm.opsin.NameToInchi;
import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.NameToStructureConfig;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		

		NameToStructure nts = NameToStructure.getInstance();
		NameToStructureConfig ntsconfig = new NameToStructureConfig();
		// a new NameToStructureConfig starts as a copy of OPSIN's default
		// configuration
		ntsconfig.setAllowRadicals(true);
		OpsinResult result = nts.parseChemicalName("acetonitrile", ntsconfig);
		Element cml = result.getCml();
		String smiles = result.getSmiles();
		System.out.println(smiles);
		System.out.println(cml);
		
		String inchi = NameToInchi.convertResultToInChI(result);
		String stdinchi = NameToInchi.convertResultToStdInChI(result);
		System.out.println(inchi + "\n" + stdinchi + "\n");
			
		System.out.println("================================================================================");
		System.out.println("================================================================================");
		
		OPSINFacade facade = new OPSINFacade();
		facade.getSmiles("2,4,6-trinitrotoluene");
		facade.getCml("2,4,6-trinitrotoluene");
		facade.getInChi("2,4,6-trinitrotoluene");
		facade.getStandardInChi("2,4,6-trinitrotoluene");
		
		facade.toString();
		
		System.out.println("\n\n" + facade + "\n\n");
	}
}