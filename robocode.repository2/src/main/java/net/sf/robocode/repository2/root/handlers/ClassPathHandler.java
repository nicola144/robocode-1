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
import net.sf.robocode.repository2.root.ClassPathRoot;
import net.sf.robocode.repository2.Database;
import net.sf.robocode.io.Logger;

import java.util.Hashtable;
import java.io.File;
import java.net.MalformedURLException;

/**
 * @author Pavel Savara (original)
 */
public class ClassPathHandler extends RootHandler{
	public void visitDirectory(File dir, boolean isDevel, Hashtable<String, IRepositoryRoot> newroots, Hashtable<String, IRepositoryRoot> roots, Database db) {
		try {
			final String key = dir.toURL().toString();
			IRepositoryRoot root = roots.get(key);

			if (root == null) {
				root = new ClassPathRoot(db, dir);
			}else{
				roots.remove(key);
			}
			root.update();
			newroots.put(dir.toURL().toString(), root);
		} catch (MalformedURLException e) {
			Logger.logError(e);
		}
	}
}
