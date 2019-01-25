/********************************************************************************
** Form generated from reading UI file 'sda_proiect_do.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SDA_PROIECT_DO_H
#define UI_SDA_PROIECT_DO_H

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

class Ui_Sda_Proiect_DOClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Sda_Proiect_DOClass)
    {
        if (Sda_Proiect_DOClass->objectName().isEmpty())
            Sda_Proiect_DOClass->setObjectName(QStringLiteral("Sda_Proiect_DOClass"));
        Sda_Proiect_DOClass->resize(600, 400);
        menuBar = new QMenuBar(Sda_Proiect_DOClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        Sda_Proiect_DOClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Sda_Proiect_DOClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        Sda_Proiect_DOClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(Sda_Proiect_DOClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        Sda_Proiect_DOClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(Sda_Proiect_DOClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        Sda_Proiect_DOClass->setStatusBar(statusBar);

        retranslateUi(Sda_Proiect_DOClass);

        QMetaObject::connectSlotsByName(Sda_Proiect_DOClass);
    } // setupUi

    void retranslateUi(QMainWindow *Sda_Proiect_DOClass)
    {
        Sda_Proiect_DOClass->setWindowTitle(QApplication::translate("Sda_Proiect_DOClass", "Sda_Proiect_DO", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class Sda_Proiect_DOClass: public Ui_Sda_Proiect_DOClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SDA_PROIECT_DO_H
