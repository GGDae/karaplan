# Google App Engine

This example uses [App Engine](https://cloud.google.com/appengine/) to run the application in a completely managed serverless service, with automatic scaling.

## Prerequisites

Before starting, follow the [SQL](../sql) guide to create the database.

## Configure the application

Copy the `src/main/appengine/files/application.example.yml` as `application.yml` in the same directory. Open it with your preferred editor and replace `toComplete` with appropriate values. Refer to the deployment [README](../../README.md) file for information about configuring identity providers.

## Deploy the application

Use the following commands in [Cloud Shell](https://cloud.google.com/shell/) or anywhere the [Cloud SDK](https://cloud.google.com/sdk/) is installed:

    # Set variables, adjust them as needed
    REGION="europe-west1"

    # Create app in region (warning: can't be changed later!)
    gcloud app create --region=$REGION

    # Get app source code if necessary
    git clone https://github.com/fcrespel/karaplan.git
    cd karaplan

    # Build and deploy app with Maven
    ./mvnw -Dfrontend-build -Dappengine-build -DskipTests package appengine:deploy

After completion, the application should be available at `https://<project-id>.appspot.com`
