package com.github.janssk1.maven.plugin.graph;

import com.github.janssk1.maven.plugin.graph.graph.Graph;

import java.io.IOException;
import java.io.Writer;

/**
 * User: janssk1
 * Date: 10/5/11
 * Time: 12:24 PM
 */
public interface GraphSerializer {

    void serialize(Graph graph, Writer writer, RenderOptions options) throws IOException;


}
