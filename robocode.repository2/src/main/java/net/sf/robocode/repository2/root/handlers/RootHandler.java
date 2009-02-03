/*******************************************************************************
 * Copyright (c) 2001, 2009 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Pavel Savara
 *     - Initial implementation
 *******************************************************************************/
package net.sf.robocode.repository2.root.handlers;

import net.sf.robocode.repository2.root.IRepositoryRoot;
import net.sf.robocode.repository2.Database;
import net.sf.robocode.repository2.items.IItem;
import net.sf.robocode.core.Container;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Pavel Savara (original)
 */
public abstract class RootHandler {
	public abstract void visitDirectory(File dir, boolean isDevel, Hashtable<String, IRepositoryRoot> newroots, Hashtable<String, IRepositoryRoot> roots, Database db);

	public static void visitDirectories(File dir, boolean isDevel, Hashtable<String, IRepositoryRoot> newroots, Hashtable<String, IRepositoryRoot> roots, Database db){
		// walk thru all plugins
		final List<RootHandler> itemHandlerList = Container.getComponents(RootHandler.class);
		for (RootHandler handler : itemHandlerList) {
			handler.visitDirectory(dir, isDevel, newroots, roots, db);
		}
	}
}
