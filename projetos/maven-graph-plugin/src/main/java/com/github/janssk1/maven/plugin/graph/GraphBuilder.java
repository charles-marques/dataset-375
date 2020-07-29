package com.github.janssk1.maven.plugin.graph;

import com.github.janssk1.maven.plugin.graph.domain.ArtifactRevisionIdentifier;
import com.github.janssk1.maven.plugin.graph.graph.Graph;

/**
 * User: janssk1
 * Date: 8/13/11
 * Time: 9:30 PM
 */
public interface GraphBuilder {

    Graph buildGraph(ArtifactRevisionIdentifier artifact, DependencyOptions options);
}