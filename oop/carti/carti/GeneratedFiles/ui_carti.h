/********************************************************************************
** Form generated from reading UI file 'carti.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CARTI_H
#define UI_CARTI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_cartiClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *cartiClass)
    {
        if (cartiClass->objectName().isEmpty())
            cartiClass->setObjectName(QStringLiteral("cartiClass"));
        cartiClass->resize(600, 400);
        menuBar = new QMenuBar(cartiClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        cartiClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(cartiClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        cartiClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(cartiClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        cartiClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(cartiClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        cartiClass->setStatusBar(statusBar);

        retranslateUi(cartiClass);

        QMetaObject::connectSlotsByName(cartiClass);
    } // setupUi

    void retranslateUi(QMainWindow *cartiClass)
    {
        cartiClass->setWindowTitle(QApplication::translate("cartiClass", "carti", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class cartiClass: public Ui_cartiClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CARTI_H
