#!/bin/bash
set -euxo pipefail

NAMESPACE=${NAMESPACE}
helm -n $NAMESPACE install api-versioning-db bitnami/mongodb -f values.yaml
