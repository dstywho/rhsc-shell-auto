package com.redhat.qe.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.apache.http.client.methods.HttpGet;
import org.calgb.test.performance.BuildPostException;
import org.calgb.test.performance.ProcessResponseBodyException;
import org.calgb.test.performance.RequestException;
import org.calgb.test.performance.UseSslException;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.redhat.qe.config.RhscConfiguration;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.ClusterFactory;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.repository.rest.ClusterRepository;
import com.redhat.qe.repository.rest.DatacenterRepository;

public class ClusterTest extends TestBase{
	
	
	@Test 
	public void createTest() throws JAXBException, ProcessResponseBodyException, RequestException, BuildPostException{
		final Cluster cluster = RhscConfiguration.getConfiguration().getCluster();
		Datacenter defaultDatacenter = new DatacenterRepository(getSession()).list().getDatacenters().get(0);
		cluster.setDatacenter(defaultDatacenter);
		getRepository().create(cluster);


		Collection<Cluster> matchingCluster = findMatchingCluster(cluster, getRepository().list());
		getRepository().delete(matchingCluster.iterator().next());
	}

	/**
	 * @param cluster
	 * @param arrayList 
	 * @return 
	 */
	private Collection<Cluster> findMatchingCluster(final Cluster cluster, ArrayList<Cluster> clusters) {
		return Collections2.filter(clusters, new Predicate<Cluster>() {

			public boolean apply(Cluster eachCluster) {
				return cluster.getName().equals(eachCluster.getName());
			}
		});
	}

	/**
	 * @return
	 */
	private ClusterRepository getRepository() {
		return new ClusterRepository(getSession());
	}
	

}
