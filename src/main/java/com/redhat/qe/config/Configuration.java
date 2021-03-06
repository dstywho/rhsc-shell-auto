package com.redhat.qe.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.redhat.qe.exceptions.UnableToOpenConfigurationFileException;
import com.redhat.qe.factories.ClusterFactory;
import com.redhat.qe.factories.HostFactory;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.model.Host;
import com.redhat.qe.ssh.Credentials;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import dstywho.FileUtil;

public class Configuration {
	
	private RestApi restApi;
	private ShellHost shellHost;
	private Cluster cluster;
	private List<Host> hosts;
	/**
	 * @param restApi
	 * @param shellHost
	 */
	public Configuration(RestApi restApi, ShellHost shellHost) {
		super();
		this.restApi = restApi;
		this.shellHost = shellHost;
	}
	/**
	 * @return the restApi
	 */
	public RestApi getRestApi() {
		return restApi;
	}
	/**
	 * @param restApi the restApi to set
	 */
	public void setRestApi(RestApi restApi) {
		this.restApi = restApi;
	}
	/**
	 * @return the shellHost
	 */
	public ShellHost getShellHost() {
		return shellHost;
	}
	/**
	 * @param shellHost the shellHost to set
	 */
	public void setShellHost(ShellHost shellHost) {
		this.shellHost = shellHost;
	}
	
	
	
	/**
	 * @return the hosts
	 */
	public List<Host> getHosts() {
		return hosts;
	}
	/**
	 * @param hosts the hosts to set
	 */
	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}
	
	/**
	 * @return the cluster
	 */
	public Cluster getCluster() {
		return cluster;
	}
	/**
	 * @param cluster the cluster to set
	 */
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	public String toXml(){
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(this);
	}
	public static Configuration fromXml(String config){
		XStream xstream = new XStream(new DomDriver());
		return (Configuration) xstream.fromXML(config);
	}
	

	public static void main(String[] a){
//		RestApi api = new RestApi("https://localhost:443/api", new Credentials("admin@internal", "redhat"));
		RestApi api = new RestApi("10.70.37.112", new Credentials("admin@internal", "redhat"));
		ShellHost shell = new ShellHost("10.70.37.112", new Credentials("root", "redhat"),22);
		Configuration config = new Configuration(api, shell);
		Cluster clustr = ClusterFactory.cluster("myCluster");
		clustr.setMajorVersion(3);
		clustr.setMinorVersion(2);
		Datacenter dc = new Datacenter();
		dc.setName("Default");
		clustr.setDatacenter(dc);
		config.setCluster(clustr);
		ArrayList<Host> hostz = new ArrayList<Host>();
		hostz.add(HostFactory.create("node1", "rhsc-qa9-node-a", "redhat",clustr ));
		hostz.add(HostFactory.create("node2", "rhsc-qa9-node-b", "redhat",clustr ));
		config.setHosts(hostz);
		System.out.println(config.toXml());
		
		
		System.out.println("==============================");
		System.out.println("==============================");
		System.out.println("==============================");
		System.out.println("==============================");
		
		String u = fromXml(config.toXml()).getRestApi().getCredentials().getUsername();
		System.out.println(u);
		
	}

}
