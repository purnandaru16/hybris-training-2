/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information").
 *
 * You shall not disclose such Confidential
 * Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 *
 */
package org.training.core.tasks;

import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.training.core.util.TrainingUtils;

import java.io.FileInputStream;


/**
 * The Class DemoAbstractImpexRunnerTask.
 *
 * @author kris.sunu.purnandaru
 */

public abstract class TrainingAbstractImpexRunnerTask extends AbstractImpexRunnerTask {

    private static final Logger LOG = Logger.getLogger(TrainingAbstractImpexRunnerTask.class);

    @Override
    protected void processFile(final java.io.File file, final String encoding) throws java.io.FileNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            final ImportConfig config = getImportConfig();
            if (config == null) {
                LOG.error(String.format("Import config not found. The file %s won't be imported.",
                        file == null ? null : file.getName()));
                return;
            }
            final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
            config.setScript(resource);
            final ImportResult importResult = getImportService().importData(config);
            if (importResult.isError() && importResult.hasUnresolvedLines()) {
                final String unresolvedLines = importResult.getUnresolvedLines().getPreview();
                sendEmail(file, unresolvedLines);
                LOG.error(unresolvedLines);
            }
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    private void sendEmail(final java.io.File file, final String unresolvedLines) {
        final String subject = TrainingUtils.getTrainingHotFolderErrorSubject();
        final String fileName = file.getName();
        String message = TrainingUtils.getTrainingHotFolderErrorMessage() + "\n" + fileName;
        message = message + '\n' + unresolvedLines;
        final String toEmail = TrainingUtils.getTrainingHotFolderErrorEmail();
        final String[] toEmails = toEmail.split(TrainingUtils.EMAIL_ID_SEPARATOR);
        TrainingUtils.sendNotificationEmail(subject, message, toEmails);
    }
}
