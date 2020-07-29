package com.github.janssk1.maven.plugin.graph;

import com.github.janssk1.maven.plugin.graph.domain.Artifact;
import com.github.janssk1.maven.plugin.graph.domain.ArtifactRevisionIdentifier;

/**
 * User: janssk1
 * Date: 8/14/11
 * Time: 12:19 AM
 */
public interface ArtifactResolver {
    Artifact resolveArtifact(ArtifactRevisionIdentifier identifier);
}
