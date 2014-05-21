package com.mestrelab.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import uk.ac.cam.ch.wwmm.opsin.NameToInchi;
import uk.ac.cam.ch.wwmm.opsin.NameToStructure;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult;
import uk.ac.cam.ch.wwmm.opsin.OpsinResult.OPSIN_RESULT_STATUS;

public class OPSINFacade {
	private String smiles;
	private String cml;
	private String inChi;
	private String standardInChi;
	private String molfile;
	private static NameToStructure nts;
	private static NameToInchi nti;

	static {
		setNts(NameToStructure.getInstance());
		setNti(new NameToInchi());
	}

	public OPSINFacade() {

	}

	private boolean checkChemicalName(String mol)
			throws ParseChemicalNameException {

		OpsinResult result = getNts().parseChemicalName(mol);
		if (result.getStatus().equals(OPSIN_RESULT_STATUS.FAILURE))
			throw new ParseChemicalNameException(result.getMessage());
		return true;
	}

	public ResponseVO getSmiles(String mol) {
		NameToStructure nts = getNts();
		ResponseVO response = new ResponseVO();
		try {
			if (checkChemicalName(mol)) {
				setSmiles(nts.parseToSmiles(mol));
				response.setTypeError(0);
			}
			response.setTypeMessage(TypeMessage.SMILES);
			response.setMessage(this.smiles);
		} catch (ParseChemicalNameException e) {
			response.setTypeError(-1);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public ResponseVO getCml(String mol) {
		NameToStructure nts = getNts();
		ResponseVO response = new ResponseVO();
		try {
			if (checkChemicalName(mol)) {
				String result = nts.parseToCML(mol).toXML();
				setCml(result);
				response.setTypeError(0);
			}

			response.setTypeMessage(TypeMessage.CML);
			response.setMessage(this.cml);
		} catch (ParseChemicalNameException e) {
			response.setTypeError(-1);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public ResponseVO getInChi(String mol) {
		ResponseVO response = new ResponseVO();
		try {
			if (checkChemicalName(mol)) {
				setInChi(getNti().parseToInchi(mol));
				response.setTypeError(0);
			}
			
			response.setTypeMessage(TypeMessage.INCHI);
			response.setMessage(this.inChi);
		} catch (ParseChemicalNameException e) {
			response.setTypeError(-1);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	public ResponseVO getStandardInChi(String mol) {
		ResponseVO response = new ResponseVO();
		try {
			if (checkChemicalName(mol)) {
				setStandardInChi(getNti().parseToStdInchi(mol));
				response.setTypeError(0);
			}
			response.setTypeMessage(TypeMessage.STANDARDINCHI);
			response.setMessage(this.standardInChi);
		} catch (ParseChemicalNameException e) {
			response.setTypeError(-1);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	public ResponseVO getMolfile(String mol) {
		ResponseVO response = new ResponseVO();
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			this.molfile = "";
			if (checkChemicalName(mol)) {
			       this.molfile = getOpenbabelMolfile(getSmiles(mol).getMessage());
				response.setTypeError(0);
			}
			
			response.setTypeMessage(TypeMessage.MOLFILE);
			response.setMessage(this.molfile);

		} catch (ParseChemicalNameException e) {
			response.setTypeError(-1);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	public String toString() {
		String cadea = "Smiles: " + smiles + "\nCml: " + cml + "\nInChi "
				+ inChi + "\nStandard inChi: " + standardInChi + "\nMolfile: " + molfile;
		return cadea;
	}

	private void setSmiles(String smiles) {
		this.smiles = smiles;
	}

	private void setCml(String data) {
		this.cml = data;
	}

	private void setInChi(String inChi) {
		this.inChi = inChi;
	}

	private void setStandardInChi(String standardInChi) {
		this.standardInChi = standardInChi;
	}

	public NameToStructure getNts() {
		return nts;
	}

	public static void setNts(NameToStructure ntsParam) {
		nts = ntsParam;
	}

	public NameToInchi getNti() {
		return nti;
	}

	public static void setNti(NameToInchi ntiParam) {
		nti = ntiParam;
	}
	
    private String getOpenbabelMolfile(String smiles) throws ParseChemicalNameException {	
	Process p;
	String os = System.getProperty("os.name").toLowerCase();
	try {
	    if (os.indexOf("win") >= 0) {
	    p = Runtime.getRuntime().exec(
	    	"obabel -:\"" + smiles + "\" -omol --gen2D -d");
	    }
	    else{
		  p = Runtime.getRuntime().exec(
			    	"/usr/local/bin/obabel -:" + smiles + " -omol --gen2D -d");
	    }
	
	BufferedReader stdInput = new BufferedReader(new InputStreamReader(
		p.getInputStream()), 8 * 1024);

	BufferedReader stdError = new BufferedReader(new InputStreamReader(
		p.getErrorStream()));

	// read the output from the command

	StringBuffer result = new StringBuffer();
	String s = "";
	while ((s = stdInput.readLine()) != null)
	    result.append(s.replace("[", "").replace("]", "")).append("\r\n");
	return result.toString();	
	} catch (IOException e) {
	   throw new ParseChemicalNameException(e.getMessage());
	}
    }
}
