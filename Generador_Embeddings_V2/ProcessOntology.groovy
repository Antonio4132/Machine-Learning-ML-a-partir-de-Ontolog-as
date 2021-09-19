@Grapes([
    @Grab(group="org.semanticweb.elk", module="elk-owlapi", version="0.4.3"),
    @Grab(group="net.sourceforge.owlapi", module="owlapi-api", version="4.2.5"),
    @Grab(group="net.sourceforge.owlapi", module="owlapi-apibinding", version="4.2.5"),
    @Grab(group="net.sourceforge.owlapi", module="owlapi-impl", version="4.2.5"),
    @Grab(group="net.sourceforge.owlapi", module="owlapi-parsers", version="4.2.5"),
    @Grab(group="org.codehaus.gpars", module="gpars", version="1.1.0"),
    @Grab(group="net.sourceforge.owlapi", module="org.semanticweb.hermit", version="1.3.8.413"),
    @GrabConfig(systemClassLoader=true)
])

import org.semanticweb.owlapi.model.parameters.*;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.elk.owlapi.ElkReasonerConfiguration;
import org.semanticweb.elk.reasoner.config.*;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.reasoner.*;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.io.*;
import org.semanticweb.owlapi.owllink.*;
import org.semanticweb.owlapi.util.*;
import org.semanticweb.owlapi.search.*;
import org.semanticweb.owlapi.manchestersyntax.renderer.*;
import org.semanticweb.owlapi.reasoner.structural.*;
import uk.ac.manchester.cs.owlapi.modularity.ModuleType;
import uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;
import org.semanticweb.owlapi.manchestersyntax.renderer.*;
import java.io.*;
import java.io.PrintWriter;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.InferredEquivalentClassAxiomGenerator;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import groovyx.gpars.GParsPool;


OWLOntologyManager outputManager = OWLManager.createOWLOntologyManager();
OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
String ontostring = args[0];
String prefreasoner =args[1];
OWLOntology ont = manager.loadOntologyFromOntologyDocument(new File(ontostring));
public class SimpleShortFormProvider1 implements ShortFormProvider, Serializable {

    private final SimpleIRIShortFormProvider uriShortFormProvider = new SimpleIRIShortFormProvider();

    @Override
    public String getShortForm(OWLEntity entity) {
	return '<'+entity.getIRI().toString()+'>';
    }
    public void dispose(){
	;
    }
}
if (prefreasoner.toLowerCase().equals("elk"))
    {

    OWLDataFactory dataFactory = manager.getOWLDataFactory()
    OWLDataFactory fac = manager.getOWLDataFactory()

    ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor()
    OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor)


    ElkReasonerFactory f1 = new ElkReasonerFactory()
    OWLReasoner reasoner = f1.createReasoner(ont, config)
    reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY)

    List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
    gens.add(new InferredSubClassAxiomGenerator());
    gens.add(new InferredEquivalentClassAxiomGenerator());
    OWLOntology infOnt = outputManager.createOntology();


    InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner,gens);
    iog.fillOntology(outputManager.getOWLDataFactory(), infOnt);

    // Save the inferred ontology.
    //outputManager.saveOntology(infOnt,IRI.create((new File("inferredontologygo2.owl").toURI())));

    // Display Axioms
    OWLObjectRenderer renderer =new ManchesterOWLSyntaxOWLObjectRendererImpl ();
    renderer.setShortFormProvider(new SimpleShortFormProvider1());
    int numaxiom1= infOnt.getAxiomCount();
    Set<OWLClass> classes=infOnt.getClassesInSignature();

    //display original axioms
    //int numaxiom1= Ont.getAxiomCount();
    Set<OWLClass> classeso=ont.getClassesInSignature();

    FileWriter fwo= new FileWriter ("axiomsorig.lst");
    BufferedWriter bwo =new BufferedWriter (fwo);
    PrintWriter outo =new PrintWriter (bwo);

    for (OWLClass classo : classeso)
	{
	Set<OWLClassAxiom> ontoaxioms=ont.getAxioms (classo);
	for (OWLClassAxiom claxiom: ontoaxioms)
	    {
	    // classess=renderer.render(class1);
	    classaxiom=renderer.render (claxiom);
	    //out1.println (classess);
	    outo.println (classaxiom.replaceAll("\n"," ").replaceAll(","," "));
	}
    }
    outo.flush() //Tilman comment
} else if (prefreasoner.toLowerCase().equals("elk2")) {

	OWLDataFactory dataFactory = manager.getOWLDataFactory()
	OWLDataFactory fac = manager.getOWLDataFactory()

	ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor()
	OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor)


	//ElkReasonerFactory f1 = new ElkReasonerFactory()
	//OWLReasoner reasoner = f1.createReasoner(ont, config)
	//reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY)

	StructuralReasonerFactory factory = new StructuralReasonerFactory();
	reasoner = factory.createReasoner(ont, config);
	reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
	
	List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
	gens.add(new InferredSubClassAxiomGenerator());
	gens.add(new InferredEquivalentClassAxiomGenerator());
	OWLOntology infOnt = outputManager.createOntology();


	InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner,gens);
	iog.fillOntology(outputManager.getOWLDataFactory(), infOnt);

	// Save the inferred ontology.
	//outputManager.saveOntology(infOnt,IRI.create((new File("inferredontologygo2.owl").toURI())));

	// Display Axioms
	OWLObjectRenderer renderer =new ManchesterOWLSyntaxOWLObjectRendererImpl ();
	renderer.setShortFormProvider(new SimpleShortFormProvider1());
	int numaxiom1= infOnt.getAxiomCount();
	Set<OWLClass> classes=infOnt.getClassesInSignature();

	//display original axioms
	//int numaxiom1= Ont.getAxiomCount();
	Set<OWLClass> classeso=ont.getClassesInSignature();

	FileWriter fwo= new FileWriter ("axioms.lst");
	BufferedWriter bwo =new BufferedWriter (fwo);
	PrintWriter outo =new PrintWriter (bwo);
	
	for (OWLClass classo : classeso)
		{
		Set<OWLClassAxiom> ontoaxioms=ont.getAxioms (classo);
		for (OWLClassAxiom claxiom: ontoaxioms)
			{
			// classess=renderer.render(class1);
			classaxiom=renderer.render (claxiom);
			//out1.println (classess);
			outo.println (classaxiom.replaceAll("\n"," ").replaceAll(","," "));
		}
		}
	outo.flush() //Tilman comment
	
	FileWriter fwo2= new FileWriter ("instances.lst");
	BufferedWriter bwo2 =new BufferedWriter (fwo2);
	PrintWriter outo2 =new PrintWriter (bwo2);
	
	for (OWLClass classo : classes)
	{
		
			NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(classo, true);
			//System.out.println("The Individuals of my class : ");
		
			for (OWLNamedIndividual i : instances.getFlattened()) {
				outo2.out.println(renderer.render(i) + " instanceOf " + renderer.render(classo));
				for(OWLObjectProperty objProp : ont.getObjectPropertiesInSignature(true)) {
					for(OWLNamedIndividual ind : reasoner.getObjectPropertyValues(i, objProp).getFlattened()) {
						obpStr = renderer.render(objProp).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">","");
						//if(!obpStr.equals("SPR.hasSpecie")) {
							outo2.out.println(renderer.render(i) + " " + renderer.render(objProp) + " " + renderer.render(ind));
						//}
					}
				}
			}
	}
			
	outo2.flush() //Tilman comment
	
	FileWriter fwo3= new FileWriter ("projects_metadata.lst");
	BufferedWriter bwo3 =new BufferedWriter (fwo3);
	PrintWriter outo3 =new PrintWriter (bwo3);
	
	for (OWLClass classo : classes)
	{
			if (renderer.render(classo).equals("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#Project>")) {
				
				NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(classo, true);
				//System.out.println("The Individuals of my class : ");
			
				for (OWLNamedIndividual i : instances.getFlattened()) {
					outo3.out.println(renderer.render(i).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">",""));
					for(OWLObjectProperty objProp : ont.getObjectPropertiesInSignature(true)) {
						for(OWLNamedIndividual ind : reasoner.getObjectPropertyValues(i, objProp).getFlattened()) {
							outo3.out.println(renderer.render(objProp).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">","") + " " + renderer.render(ind).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">",""));
						}
					}
					outo3.out.println("");
				}
			}
	}
	
	outo3.flush() //Tilman comment
	
	FileWriter fwo4= new FileWriter ("species.csv");
	BufferedWriter bwo4 =new BufferedWriter (fwo4);
	PrintWriter outo4 =new PrintWriter (bwo4);
	
	for (OWLClass classo : classes)
	{
			if (renderer.render(classo).equals("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#Project>")) {
				
				NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(classo, true);
				//System.out.println("The Individuals of my class : ");
			
				for (OWLNamedIndividual i : instances.getFlattened()) {
					for(OWLObjectProperty objProp : ont.getObjectPropertiesInSignature(true)) {
						obpStr = renderer.render(objProp).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">","");
						//outo4.out.println(obpStr);
						if (obpStr.equals("SPR.hasSpecie")) {
							for(OWLNamedIndividual ind : reasoner.getObjectPropertyValues(i, objProp).getFlattened()) {
								instance = renderer.render(i).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">","");
								instanceProp = renderer.render(ind).replace("<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#","").replace(">","")
								outo4.out.println(instance + " " + instanceProp);
								break;
								
							}
						}
					}
				}
			}
	}
	
	outo4.flush() //Tilman comment
	
	FileWriter fwo5= new FileWriter ("ann_corpus.txt");
	BufferedWriter bwo5 =new BufferedWriter (fwo5);
	PrintWriter outo5 =new PrintWriter (bwo5);
	
	for (OWLClass classo : classes)
	{
		
			NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(classo, true);
			//System.out.println("The Individuals of my class : ");
		
			for (OWLNamedIndividual i : instances.getFlattened()) {
				for(OWLAnnotationAssertionAxiom annotations: ont.getAnnotationAssertionAxioms(i.getIRI())) {
                    	outo5.out.println(renderer.render(i) + " " + annotations.getProperty() +" "+annotations.getValue().toString().replace("\"",""));
				}

			}
	}
	
	outo5.flush()
			
} else {
    OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
    OWLReasoner reasoner =reasonerFactory.createReasoner(ont);
    OWLDataFactory factory=manager.getOWLDataFactory();
    reasoner.precomputeInferences();
    InferredSubClassAxiomGenerator generator = new InferredSubClassAxiomGenerator();
    Set<OWLAxiom> axioms = generator.createAxioms(factory, reasoner);
    manager.addAxioms(ont,axioms);
    OWLObjectRenderer renderer =new ManchesterOWLSyntaxOWLObjectRendererImpl ();
    renderer.setShortFormProvider(new SimpleShortFormProvider1());
    Set<OWLClass> classes=ont.getClassesInSignature();

    FileWriter fw= new FileWriter ("axiomsinf.lst");
    BufferedWriter bw =new BufferedWriter (fw);
    PrintWriter out =new PrintWriter (bw);

    FileWriter fw1= new FileWriter ("classes.lst");
    BufferedWriter bw1 =new BufferedWriter (fw1);
    PrintWriter out1 =new PrintWriter (bw1);

    for (OWLClass class1 : classes)
	{
	Set<OWLClassAxiom> ontoaxioms=ont.getAxioms (class1);
	for (OWLClassAxiom claxiom: ontoaxioms)
	    {
	    classess=renderer.render(class1);
	    classaxiom=renderer.render (claxiom);
	    out1.println (classess);
	    out.println (classaxiom.replaceAll("\n"," ").replaceAll(","," "));
	}
    }

    FileWriter fwo= new FileWriter ("axiomsorig.lst"); BufferedWriter bwo =new BufferedWriter (fwo); PrintWriter outo =new PrintWriter (bwo);
    outo.println(" ");
    outo.flush()
    outo.close()
}
