/********************************************************************************
** Form generated from reading UI file 'ceva.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CEVA_H
#define UI_CEVA_H

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

class Ui_cevaClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *cevaClass)
    {
        if (cevaClass->objectName().isEmpty())
            cevaClass->setObjectName(QStringLiteral("cevaClass"));
        cevaClass->resize(600, 400);
        menuBar = new QMenuBar(cevaClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        cevaClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(cevaClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        cevaClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(cevaClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        cevaClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(cevaClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        cevaClass->setStatusBar(statusBar);

        retranslateUi(cevaClass);

        QMetaObject::connectSlotsByName(cevaClass);
    } // setupUi

    void retranslateUi(QMainWindow *cevaClass)
    {
        cevaClass->setWindowTitle(QApplication::translate("cevaClass", "ceva", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class cevaClass: public Ui_cevaClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CEVA_H
