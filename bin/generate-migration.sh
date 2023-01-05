#!/usr/bin/env bash
set -eo pipefail

function joinBy { local IFS="$1"; shift; echo "$*"; }

MIGRATION_FILE_PATH=V$(date +"%Y_%m_%d_%H_%M_%S")__$(joinBy "_" "$@").sql
echo "SET ROLE binding_group;" >> "$MIGRATION_FILE_PATH"
echo "Generated migration file at path $MIGRATION_FILE_PATH"
