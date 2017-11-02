package org.codebella.java.drool;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolApplication {
	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-card-rules");
			Card card1 = new Card("King", 13);
			kSession.insert(card1);
			kSession.insert(new Card("Jack", 11));
			kSession.insert(new Card("Queen", 12));
			kSession.insert(new Card("Number Card", 3));
			kSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
