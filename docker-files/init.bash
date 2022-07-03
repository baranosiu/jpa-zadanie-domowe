#!/bin/bash
source .env
export PGPASSWORD=${POSTGRES_PASSWORD}
/opt/postgresql/bin/psql -h localhost -U ${POSTGRES_USER} -w -d postgres -f init.sql
