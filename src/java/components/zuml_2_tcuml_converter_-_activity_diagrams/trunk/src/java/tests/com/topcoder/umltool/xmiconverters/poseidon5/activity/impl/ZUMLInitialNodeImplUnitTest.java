/*
 * Copyright (C) 2007 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.umltool.xmiconverters.poseidon5.activity.impl;

import com.topcoder.uml.model.collaborations.collaborationinteractions.CollaborationImpl;
import com.topcoder.uml.model.core.ModelElement;
import com.topcoder.uml.model.core.Namespace;
import com.topcoder.uml.model.core.auxiliaryelements.CommentImpl;
import com.topcoder.uml.model.core.auxiliaryelements.TemplateArgumentImpl;
import com.topcoder.uml.model.core.auxiliaryelements.TemplateParameter;
import com.topcoder.uml.model.core.auxiliaryelements.TemplateParameterImpl;
import com.topcoder.uml.model.core.dependencies.DependencyImpl;
import com.topcoder.uml.model.core.extensionmechanisms.StereotypeImpl;
import com.topcoder.uml.model.core.extensionmechanisms.TaggedValueImpl;
import com.topcoder.uml.model.datatypes.PseudostateKind;
import com.topcoder.uml.model.modelmanagement.ElementImportImpl;
import com.topcoder.uml.model.statemachines.Pseudostate;
import com.topcoder.uml.model.statemachines.StateMachineImpl;

import com.topcoder.umltool.xmiconverters.poseidon5.activity.ZUMLActivityNode;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * <p>
 * Unit tests for <code>ZUMLInitialNodeImpl</code> class.
 * </p>
 *
 * @author zhengjuyu
 * @version 1.0
 */
public class ZUMLInitialNodeImplUnitTest extends TestCase {
    /**
     * <p>Returns the test suite of this class.</p>
     *
     * @return the test suite of this class.
     */
    public static Test suite() {
        return new TestSuite(ZUMLInitialNodeImplUnitTest.class);
    }

    /**
     * <p>
     * Accuracy test for constructor <code>ZUMLInitialNodeImpl()</code>.
     * </p>
     *
     * <p>
     * The constructor is a trivial one, just test whether the instance can be created.
     * </p>
     */
    public void testCtor() {
        ZUMLActivityNode node = new ZUMLInitialNodeImpl();
        assertNotNull("Unable to create ZUMLInitialNodeImpl instance.", node);
    }

    /**
     * <p>
     * Accuracy test for <code>convertToTCUML()</code>.
     * </p>
     *
     * <p>
     * Verify that the returned ModelElement is instance of Pseudostate, and its
     * kind is PseudostateKind.INITIAL. And verify that simple attributes are copied
     * to the converted instance.
     * </p>
     */
    public void testConvertToTCUML1() {
        AbstractZUMLActivityNode node = new ZUMLInitialNodeImpl();

        //Set its simple attributes.
        node.setName("initial");

        Namespace namespace = new CollaborationImpl();
        node.setNamespace(namespace);

        node.setVisibility(null);
        node.setSpecification(true);

        ModelElement element = node.convertToTCUML();

        assertTrue("The ZUMLInitialNodeImpl should be converted to Pseudostate class.",
            element instanceof Pseudostate);

        assertEquals("The kind of the state is PseudostateKind.INITIAL",
            PseudostateKind.INITIAL, ((Pseudostate) element).getKind());

        //Verify the simple attributes.
        assertEquals("The name should match.", "initial", element.getName());
        assertEquals("The namespace should match.", namespace,
            element.getNamespace());
        assertNull("The visibility is set to null.", element.getVisibility());
        assertTrue("The specification is set to true.",
            element.isSpecification());
    }

    /**
     * <p>
     * Accuracy test for <code>convertToTCUML()</code>.
     * </p>
     *
     * <p>
     * Verify that the common attribute from ModelElement is copied to the converted instance.
     * Complex attributes are verified in this test case. Simple attributes are verified in
     * the previous test case.
     * </p>
     */
    public void testConvertToTCUML2() {
        AbstractZUMLActivityNode node = new ZUMLInitialNodeImpl();

        node.addStereotype(new StereotypeImpl());
        node.addTaggedValue(new TaggedValueImpl());
        node.addReferenceTag(new TaggedValueImpl());
        node.addSupplierDependency(new DependencyImpl());
        node.addClientDependency(new DependencyImpl());
        node.addComment(new CommentImpl());

        TemplateParameter tp = new TemplateParameterImpl();
        node.setParameterTemplate(tp);

        node.addDefaultParameter(new TemplateParameterImpl());
        node.addTemplateParameter(new TemplateParameterImpl());
        node.addTemplateArgument(new TemplateArgumentImpl());
        node.addElementImport(new ElementImportImpl());
        node.addBehavior(new StateMachineImpl());

        ModelElement element = node.convertToTCUML();

        assertEquals("There should be 1 stereotype.", 1,
            element.getStereotypes().size());
        assertEquals("There should be 0 taggedValue.", 0,
            element.getTaggedValues().size());
        assertEquals("There should be 1 referenceTag.", 1,
            element.getReferenceTags().size());
        assertEquals("There should be 1 supplierDependency.", 1,
            element.getSupplierDependencies().size());
        assertEquals("There should be 1 clientDependency.", 1,
            element.getClientDependencies().size());
        assertEquals("There should be 1 comment.", 1,
            element.getComments().size());
        assertEquals("The parameterTemplate should match.", tp,
            element.getParameterTemplate());
        assertEquals("There should be 1 defaultParameter.", 1,
            element.getDefaultParameters().size());
        assertEquals("There should be 1 templateParameter.", 1,
            element.getTemplateParameters().size());
        assertEquals("There should be 1 templateArgument.", 1,
            element.getTemplateArguments().size());
        assertEquals("There should be 1 elementImport.", 1,
            element.getElementImports().size());
        assertEquals("There should be 1 behavior.", 1,
            element.getBehaviors().size());
    }

    /**
     * <p>
     * Accuracy test for <code>convertToTCUML()</code>.
     * </p>
     *
     * <p>
     * Verify that convertToTCUML() always returns the same instance.
     * </p>
     */
    public void testConvertToTCUML3() {
        ZUMLActivityNode node = new ZUMLInitialNodeImpl();

        assertEquals("The convertToTCUML() should always return the same instance.",
            node.convertToTCUML(), node.convertToTCUML());
    }
}