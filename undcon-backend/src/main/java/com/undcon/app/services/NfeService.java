package com.undcon.app.services;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Component;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

@Component
public class NfeService {

	public TEnviNFe load(String xml) throws Exception {
		try {
			TEnviNFe nfe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);
			return nfe;
		} catch (JAXBException ex) {
			throw new Exception(ex.toString());
		}
	}

	public static void main(String[] args) throws Exception {

		String caminhoXML = "NFe_assinada2.xml";

		// Le Xml do Arquivo
		String xml = XmlNfeUtil.leXml(caminhoXML);
		TEnviNFe load = new NfeService().load(xml);
		System.out.println(load.getNFe().size());
		TNFe x = load.getNFe().get(0);
		System.out.println(x);
		System.out
				.println("Emissor: " + x.getInfNFe().getEmit().getCNPJ() + " - " + x.getInfNFe().getEmit().getXNome());
		List<Det> det = x.getInfNFe().getDet();
		for (Det item : det) {
			System.out.println(item.getProd().getXProd() + " - " + item.getProd().getCEAN() + " - R$ "+item.getProd().getVProd() + " - Qauntidade: "+ item.getProd().getQCom());
		}
	}
}
