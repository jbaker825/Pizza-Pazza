#!/bin/bash
#export PGPASSWORD='postgres1'
#BASEDIR=$(dirname $0)
#DATABASE=pizza-ordering-db
#
#psql -U postgres -f "$BASEDIR/dropdb.sql" &&
#createdb -U postgres $DATABASE &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"

export PGPASSWORD='BNQ7cOAdOfHzWtdSRd8R'
BASEDIR=$(dirname $0)
DATABASE=railway
psql -h containers-us-west-183.railway.app -U postgres -p 7713 -d railway -f "$BASEDIR/dropdb.sql" &&
psql -h containers-us-west-183.railway.app -U postgres -p 7713 -d railway -f "$BASEDIR/schema.sql" &&
psql -h containers-us-west-183.railway.app -U postgres -p 7713 -d railway -f "$BASEDIR/data.sql" &&
psql -h containers-us-west-183.railway.app -U postgres -p 7713 -d railway -f "$BASEDIR/user.sql"