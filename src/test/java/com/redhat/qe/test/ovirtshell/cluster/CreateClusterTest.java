package com.redhat.qe.test.ovirtshell.cluster;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.redhat.qe.annoations.Tcms;
import com.redhat.qe.exceptions.UnexpectedReponseException;
import com.redhat.qe.factories.ClusterFactory;
import com.redhat.qe.helpers.ResponseMessageMatcher;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.test.ovirtshell.RhscShellSessionTestBase;

public class CreateClusterTest extends RhscShellSessionTestBase {


	@Tcms({"167062","233396", "250979"})
	@Test
	public void createCluster() {
		Cluster c = getClusterRepository().createOrShow(ClusterFactory.cluster("rhsccluster"));
		getClusterRepository().destroy(c);
	}

	


}