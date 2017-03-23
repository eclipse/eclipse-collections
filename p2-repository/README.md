Eclipse Collections OSGi/p2 Build
=================================

This process uses Maven artifacts and converts them into OSGi bundles and makes
them available as a p2 repository.

It runs as part of the main build and expects to find the artifacts in the
current Maven reactor.

The p2 repository will be available at `org.eclipse.collections/target/repository`.

Note, the `p2-repository` POM acts as a parent for recipes and also integrates the
recipes into the build process as modules. Each recipe is a Maven project with
`eclipse-bundle-recipe` type packaging.