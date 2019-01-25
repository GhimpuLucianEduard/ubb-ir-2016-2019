/********************************************************************************
** Form generated from reading UI file 'optest2.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_OPTEST2_H
#define UI_OPTEST2_H

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

class Ui_optest2Class
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *optest2Class)
    {
        if (optest2Class->objectName().isEmpty())
            optest2Class->setObjectName(QStringLiteral("optest2Class"));
        optest2Class->resize(600, 400);
        menuBar = new QMenuBar(optest2Class);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        optest2Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(optest2Class);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        optest2Class->addToolBar(mainToolBar);
        centralWidget = new QWidget(optest2Class);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        optest2Class->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(optest2Class);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        optest2Class->setStatusBar(statusBar);

        retranslateUi(optest2Class);

        QMetaObject::connectSlotsByName(optest2Class);
    } // setupUi

    void retranslateUi(QMainWindow *optest2Class)
    {
        optest2Class->setWindowTitle(QApplication::translate("optest2Class", "optest2", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class optest2Class: public Ui_optest2Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_OPTEST2_H
