package com.stratio.bus

import _root_.kafka.consumer.KafkaStream
import com.stratio.streaming.commons.streams.StratioStream
import java.util.List
import com.stratio.bus.messaging.{ColumnNameValue, ColumnNameType}
import com.stratio.streaming.commons.exceptions.{StratioAPISecurityException, StratioEngineStatusException, StratioEngineOperationException}
import com.stratio.bus.dto.StratioQueryStream
import com.stratio.streaming.commons.messages.{StratioStreamingMessage, ColumnNameTypeValue}

trait IStratioStreamingAPI {
  /**
   * Initializes the StratioStreamingAPI instance.
   * @return
   */
  def initialize(): IStratioStreamingAPI

  /**
   * Creates a new stream.
   * @param streamName
   * @param columns
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def createStream(streamName: String, columns: List[ColumnNameType])

  /**
   * Adds columns to a stream.
   * @param streamName
   * @param columns
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def alterStream(streamName: String, columns: List[ColumnNameType])

  /**
   * Inserts new data into a stream.
   * @param streamName
   * @param data
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  def insertData(streamName: String, data: List[ColumnNameValue])

  /**
   * Adds a query to a stream.
   * @param streamName
   * @param query
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def addQuery(streamName: String, query: String)

  /**
   * Removes a query from a stream.
   * @param streamName
   * @param queryId
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def removeQuery(streamName: String, queryId: String)

  /**
   * Removes a stream
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def dropStream(streamName: String)

  /**
   * Starts listening to a stream.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def listenStream(streamName: String): KafkaStream[Array[Byte], StratioStreamingMessage]

  /**
   * Stops listening to a stream.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def stopListenStream(streamName: String)

  /**
   * Gets a list of the columns from a given stream.
   * @param stream
   * @return a list with the columns from the given stream
   */
  def columnsFromStream(stream: String): List[ColumnNameTypeValue]

  /**
   * Gets a list of the queries from a given stream.
   * @param stream
   * @return a list with the queries from the given stream
   */
  def queriesFromStream(stream: String): List[StratioQueryStream]

  /**
   * Gets a list of all the stream that currently exists.
   * @return a list with the streams
   */
  def listStreams(): List[StratioStream]

  //def saveToCassandra(streamName: String)
}
