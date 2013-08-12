package com.redhat.qe.model;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.redhat.qe.ssh.Response;


@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement(name="cluster")
public class Cluster {
	
	
	@XmlAttribute
	private String id;
	
	private String name;
	
	@XmlElement(name="data_center")
	private Datacenter datacenter;
	
	@XmlElement(name="version")
	private Version version;
	
	@XmlElement(name="virt_service")
	private boolean virtService = false;
	
	@XmlElement(name="gluster_service")
	private boolean glusterService = true;
	
	@XmlElement(name="cpu")
	private Cpu cpu;
	
	@XmlElement(name="description")
	private String description;
	
	public static Cluster fromResponse(Response response){
		HashMap<String, String> attributes = StringUtils.keyAttributeToHash(response.toString());
		Cluster cluster = new Cluster();
		cluster.setId(attributes.get("id"));
		cluster.setName(attributes.get("name"));
		return cluster;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

	/**
	 * @return the datacenter
	 */
	public Datacenter getDatacenter() {
		return datacenter;
	}

	/**
	 * @param datacenter the datacenter to set
	 */
	public void setDatacenter(Datacenter datacenter) {
		this.datacenter = datacenter;
	}

	/**
	 * @return the version
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * @return the virtService
	 */
	public boolean isVirtService() {
		return virtService;
	}

	/**
	 * @param virtService the virtService to set
	 */
	public void setVirtService(boolean virtService) {
		this.virtService = virtService;
	}

	/**
	 * @return the glusterService
	 */
	public boolean isGlusterService() {
		return glusterService;
	}

	/**
	 * @param glusterService the glusterService to set
	 */
	public void setGlusterService(boolean glusterService) {
		this.glusterService = glusterService;
	}

	/**
	 * @return the cpu
	 */
	public Cpu getCpu() {
		return cpu;
	}

	/**
	 * @param cpu the cpu to set
	 */
	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public static void main(String[] args) throws JAXBException{
		Cluster c = new Cluster();
		c.setName("hi");
		c.setDatacenter(new Datacenter(){{setId("id");}});
		c.setGlusterService(true);
		c.setVirtService(false);
		c.setVersion(new Version(){{ setMajor(3); setMinor(2);}});
		JAXBContext ctx = JAXBContext.newInstance(Cluster.class);
		Marshaller m = ctx.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(c, System.out);
		StringWriter writer = new StringWriter();
		m.marshal(c, writer);
		System.out.println(writer.toString());
		
		
	}

}
