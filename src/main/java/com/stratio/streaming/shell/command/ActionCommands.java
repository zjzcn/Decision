package com.stratio.streaming.shell.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ShellException;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.stratio.streaming.api.IStratioStreamingAPI;
import com.stratio.streaming.commons.exceptions.StratioAPISecurityException;
import com.stratio.streaming.commons.exceptions.StratioEngineStatusException;

@Component
public class ActionCommands implements CommandMarker {

    @Autowired
    private IStratioStreamingAPI stratioStreamingApi;

    @CliCommand(value = "index start", help = "index stream events")
    public String indexStart(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        try {
            stratioStreamingApi.indexStream(streamName);
            return "Stream ".concat(streamName).concat(" indexed correctly");
        } catch (StratioEngineStatusException e) {
            throw new ShellException(e);
        }
    }

    @CliCommand(value = "index stop", help = "stop index stream events")
    public String indexStop(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        try {
            stratioStreamingApi.stopIndexStream(streamName);
            return "Stream ".concat(streamName).concat(" unindexed correctly");
        } catch (StratioEngineStatusException e) {
            throw new ShellException(e);
        }
    }

    @CliCommand(value = "save cassandra start", help = "start save to cassandra action")
    public String saveCassandraStart(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        try {
            stratioStreamingApi.saveToCassandra(streamName);
            return "Stream ".concat(streamName).concat(" attached to cassandra correctly");
        } catch (StratioEngineStatusException e) {
            throw new ShellException(e);
        }
    }

    // TODO implement
    @CliCommand(value = "save cassandra stop", help = "stop save to cassandra action")
    public String saveCassandraStop(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        throw new ShellException("Action not implemented yet!");
    }

    @CliCommand(value = "listen start", help = "attach stream to kafka topic")
    public String listenStart(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        try {
            stratioStreamingApi.listenStream(streamName);
            return "Stream ".concat(streamName).concat(" listened correctly");
        } catch (StratioEngineStatusException | StratioAPISecurityException e) {
            throw new ShellException(e);
        }
    }

    @CliCommand(value = "listen stop", help = "de-attach stream to kafka topic")
    public String listenStop(
            @CliOption(key = { "stream" }, help = "The stream name", mandatory = true, optionContext = "stream") final String streamName) {
        try {
            stratioStreamingApi.stopListenStream(streamName);
            return "Stream ".concat(streamName).concat(" unlistened correctly");
        } catch (StratioEngineStatusException | StratioAPISecurityException e) {
            throw new ShellException(e);
        }
    }

}
